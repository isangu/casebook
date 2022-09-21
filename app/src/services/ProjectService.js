import * as request from '@/utils/request';

const ProjectService = {};

ProjectService.selectProjectList = (spaceCode, successHandler, failHandler) => {
  return request.get(
    `/api/${spaceCode}/projects`,
    spaceCode,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ProjectService.selectProjectInfo = (spaceCode, projectId, successHandler, failHandler) => {
  return request.get(
    `/api/${spaceCode}/projects/${projectId}`,
    spaceCode,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ProjectService.createProject = (spaceCode, project, successHandler, failHandler) => {
  return request.post(
    `/api/${spaceCode}/projects`,
    project,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ProjectService.updateProject = (spaceCode, project, successHandler, failHandler) => {
  return request.put(
    `/api/${spaceCode}/projects/${project.id}`,
    project,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ProjectService.deleteProject = (spaceCode, project, successHandler, failHandler) => {
  return request.del(
    `/api/${spaceCode}/projects/${project.id}`,
    null,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

export default ProjectService;
