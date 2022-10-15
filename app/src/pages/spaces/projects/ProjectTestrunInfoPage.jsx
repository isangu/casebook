import React from 'react';
import { Page, PageContent, PageTitle } from '@/components';
import { useTranslation } from 'react-i18next';

function ProjectTestrunInfoPage() {
  const { t } = useTranslation();

  return (
    <Page className="project-overview-info-page-wrapper" list wide>
      <PageTitle>{t('테스트런')}</PageTitle>
      <PageContent flex>
        <div className="g-empty-message">
          <div>
            <div className="icon">
              <i className="fa-solid fa-circle-info" />
            </div>
            <div className="message">[ 아직 개발되지 않은 페이지입니다 ]</div>
          </div>
        </div>
      </PageContent>
    </Page>
  );
}

ProjectTestrunInfoPage.defaultProps = {};

ProjectTestrunInfoPage.propTypes = {};

export default ProjectTestrunInfoPage;