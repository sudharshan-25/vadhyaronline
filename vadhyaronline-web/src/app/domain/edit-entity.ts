export interface EditEntity<T> {

  updateEntity(updatedEntity: T);

  cancelChanges();

  deleteEntity(entity: T);

  createEntity();
}
