import {AbstractControl} from '@angular/forms';

export abstract class AbstractComponent {
  isError: boolean;
  errorMessage = '';
  successMessage = '';

  isRequiredError(field: AbstractControl, errorCode = 'required'): boolean {
    return field.touched && field.hasError(errorCode);
  }

  abstract initFormValue(): any;

}
