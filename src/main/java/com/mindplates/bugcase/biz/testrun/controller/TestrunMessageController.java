package com.mindplates.bugcase.biz.testrun.controller;

import com.mindplates.bugcase.biz.testrun.dto.TestrunParticipantDTO;
import com.mindplates.bugcase.biz.testrun.service.TestrunService;
import com.mindplates.bugcase.biz.user.dto.UserDTO;
import com.mindplates.bugcase.biz.user.service.UserService;
import com.mindplates.bugcase.common.exception.ServiceException;
import com.mindplates.bugcase.common.message.MessageSendService;
import com.mindplates.bugcase.common.message.vo.MessageData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@MessageMapping("/api/message/{spaceCode}/projects/{projectId}/testruns")
@AllArgsConstructor
public class TestrunMessageController {

    private final TestrunService testrunService;
    private final UserService userService;
    private final MessageSendService messageSendService;

    @MessageMapping("/{testrunId}/join")
    public void join(@DestinationVariable(value = "spaceCode") String spaceCode, @DestinationVariable(value = "projectId") Long projectId, @DestinationVariable(value = "testrunId") Long testrunId, SimpMessageHeaderAccessor headerAccessor) {

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();
        if (attributes == null) {
            throw new ServiceException("session.error.expired");
        }

        Long userId = Long.parseLong((String) attributes.get("USER-ID"));
        UserDTO user = userService.selectUserInfo(userId);
        TestrunParticipantDTO testrunParticipant = testrunService.createTestrunParticipantInfo(spaceCode, projectId, testrunId, user);

        // 테스트런의 모든 참가자 정보를 등록된 사용자에게 전달
        List<TestrunParticipantDTO> participants = testrunService.selectTestrunParticipantList(spaceCode, projectId, testrunId);
        MessageData participantsData = MessageData.builder().type("TESTRUN-PARTICIPANTS").build();
        participantsData.addData("participants", participants);
        messageSendService.sendTo("projects/" + projectId + "/testruns/" + testrunId + "/users/" + userId, participantsData);

        // 테스트런에 참가자들에게 새로운 사용자 정보 전달
        MessageData participantData = MessageData.builder().type("TESTRUN-USER-JOIN").build();
        participantData.addData("participant" , testrunParticipant);
        messageSendService.sendTo("projects/" + projectId + "/testruns/" + testrunId, participantData);
    }

    @MessageMapping("/{testrunId}/leave")
    public void leave(@DestinationVariable(value = "spaceCode") String spaceCode, @DestinationVariable(value = "projectId") Long projectId,
                      @DestinationVariable(value = "testrunId") Long testrunId, SimpMessageHeaderAccessor headerAccessor) {

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();
        if (attributes == null) {
            throw new ServiceException("session.error.expired");
        }

        Long userId = Long.parseLong((String) attributes.get("USER-ID"));

        testrunService.deleteTestrunParticipantInfo(spaceCode, projectId, testrunId, userId);

        List<TestrunParticipantDTO> participants = testrunService.selectTestrunParticipantList(testrunId, userId);
        participants.forEach((participant) -> {
            MessageData participantData = MessageData.builder().type("TESTRUN-USER-LEAVE").build();
            participantData.addData("participant", participant);
            messageSendService.sendTo("projects/" + participant.getProjectId() + "/testruns/" + participant.getTestrunId(), participantData);
        });
    }

    @MessageMapping("/{testrunId}/testcases/{testcaseId}/watch")
    public void join(@DestinationVariable(value = "spaceCode") String spaceCode, @DestinationVariable(value = "projectId") Long projectId, @DestinationVariable(value = "testrunId") Long testrunId, @DestinationVariable(value = "testcaseId") Long testcaseId,  SimpMessageHeaderAccessor headerAccessor) {

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();
        if (attributes == null) {
            throw new ServiceException("session.error.expired");
        }

        // 테스트런에 참가자들에게 새로운 사용자 정보 전달
        Long userId = Long.parseLong((String) attributes.get("USER-ID"));
        String userEmail = (String) attributes.get("USER-EMAIL");
        MessageData participantData = MessageData.builder().type("TESTRUN-TESTCASE-WATCH").build();
        participantData.addData("userId" , userId);
        participantData.addData("userEmail" , userEmail);
        participantData.addData("testcaseId" , testcaseId);
        messageSendService.sendTo("projects/" + projectId + "/testruns/" + testrunId, participantData);
    }


}

