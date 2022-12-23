import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { Message, ProjectBugInfoPage, ProjectEditPage, ProjectInfoPage, ProjectDashBoardPage, ProjectReportInfoPage, ProjectTestcaseInfoPage, SpaceProjectListPage } from '@/pages';
import TestrunsRoutes from '@/pages/spaces/projects/testruns';

function ProjectsRoutes() {
  return (
    <Routes>
      <Route path="/new" element={<ProjectEditPage />} />
      <Route path="/:projectId/edit" element={<ProjectEditPage type="edit" />} />
      <Route path="/:projectId/info" element={<ProjectInfoPage />} />
      <Route path="/:projectId" element={<ProjectDashBoardPage />} />
      <Route path="/:projectId/testcases" element={<ProjectTestcaseInfoPage />} />
      <Route path="/:projectId/testruns/*" element={<TestrunsRoutes />} />
      <Route path="/:projectId/bugs" element={<ProjectBugInfoPage />} />
      <Route path="/:projectId/reports" element={<ProjectReportInfoPage />} />
      <Route path="/" element={<SpaceProjectListPage />} />
      <Route path="*" element={<Message code="404" />} />
    </Routes>
  );
}

export default ProjectsRoutes;
