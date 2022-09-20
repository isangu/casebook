import { action, computed, makeObservable, observable } from 'mobx';

export default class ContextStore {
  spaceCode = null;

  projectId = null;

  constructor() {
    makeObservable(this, {
      spaceCode: observable,
      projectId: observable,
      setSpaceCode: action,
      setProjectId: action,
      isProjectSelected: computed,
    });
  }

  setSpaceCode = spaceCode => {
    this.spaceCode = spaceCode;
  };

  setProjectId = projectId => {
    this.projectId = projectId;
  };

  get isProjectSelected() {
    return !!this.spaceCode && !!this.projectId;
  }
}