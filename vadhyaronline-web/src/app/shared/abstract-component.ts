export abstract class AbstractComponent {
  isError: boolean;
  errorMessage = '';
  successMessage = '';

  abstract initFormValue(): any;
}
