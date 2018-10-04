export interface EditEntity<T> {

  updateEntity(updatedEntity?: T);

  cancelChanges();

  deleteEntity(entity: T);

  createEntity();
}

export interface ApproveEntity<T> {

  approveEntity(entity: T);

}
