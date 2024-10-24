package com.mindplates.bugcase.biz.project.entity;

import com.mindplates.bugcase.biz.space.entity.Space;
import com.mindplates.bugcase.biz.testcase.entity.TestcaseGroup;
import com.mindplates.bugcase.biz.testcase.entity.TestcaseTemplate;
import com.mindplates.bugcase.biz.testrun.entity.Testrun;
import com.mindplates.bugcase.common.constraints.ColumnsDef;
import com.mindplates.bugcase.common.entity.CommonEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project extends CommonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = ColumnsDef.NAME)
    private String name;

    @Column(name = "description", length = ColumnsDef.TEXT)
    private String description;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "token", length = ColumnsDef.CODE)
    private String token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestcaseGroup> testcaseGroups;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestcaseTemplate> testcaseTemplates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectUser> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(updatable = false, insertable = false)
    private List<ProjectApplicant> applicants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", foreignKey = @ForeignKey(name = "FK_PROJECT__SPACE"))
    private Space space;

    @Column(name = "testcase_group_seq", columnDefinition = "integer default 0")
    private Integer testcaseGroupSeq = 0;

    @Column(name = "testcase_seq", columnDefinition = "integer default 0")
    private Integer testcaseSeq = 0;

    @Column(name = "testrun_seq", columnDefinition = "integer default 0")
    private Integer testrunSeq = 0;

    @Column(name = "slack_url", length = ColumnsDef.URL)
    private String slackUrl;

    @Column(name = "enable_testrun_alarm")
    private boolean enableTestrunAlarm;

}
