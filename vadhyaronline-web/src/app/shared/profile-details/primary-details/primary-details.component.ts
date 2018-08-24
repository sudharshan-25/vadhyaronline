import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AbstractComponent} from '../../abstract-component';
import {LoginService} from '../../../services/login.service';
import {VadhyarOnlineRestService} from '../../../services/vadhyar-online-rest.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-primary-details',
  templateUrl: './primary-details.component.html',
  styleUrls: ['./primary-details.component.css']
})
export class PrimaryDetailsComponent extends AbstractComponent implements OnInit {

  primaryDetails: FormGroup;

  constructor(fb: FormBuilder, private loginService: LoginService, private restService: VadhyarOnlineRestService) {
    super();
    this.primaryDetails = fb.group({
      'firstName': new FormControl('', [Validators.required]),
      'lastName': new FormControl('', [Validators.required]),
      'userName': new FormControl('', [Validators.required]),
      'mobile': new FormControl('', [Validators.required]),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'address' : fb.group({
        'flatNumber': new FormControl('', [Validators.required]),
        'streetName': new FormControl('', [Validators.required]),
        'city': new FormControl('', [Validators.required]),
        'state': new FormControl('', [Validators.required]),
        'zipCode': new FormControl('', [Validators.required]),
      })
    });
    console.log(this.primaryDetails.controls['address']);
  }

  initFormValue() {
    this.isError = false;
    const loginUser = this.loginService.getUser();
    this.restService.getUser(loginUser.userId).subscribe(value => {
      const userDetails = value.DATA;
      const address = userDetails['address'] ? userDetails['address'] : {};
      this.primaryDetails.reset({
        'firstName': userDetails['firstName'],
        'lastName': userDetails['lastName'],
        'userName': userDetails['userName'],
        'mobile': userDetails['mobile'],
        'email': userDetails['email'],
        'address' : {
          'flatNumber' : address['flatNumber'],
          'streetName': address['streetName'],
          'city': address['city'],
          'state': address['state'],
          'zipCode': address['zipCode']
        }
      });
    }, (error: HttpErrorResponse) => {
      this.isError = true;
      this.errorMessage = error.message;
    });
  }

  ngOnInit() {
    this.initFormValue();
  }

  updateUserDetails(formValues: any) {
    this.isError = false;
    if (this.primaryDetails.valid) {
      const loginUser = this.loginService.getUser();
      this.restService.updateUser(loginUser.userId, formValues).subscribe(value => {
        this.successMessage = value.DATA;
        this.initFormValue();
      }, (error: HttpErrorResponse) => {
        this.isError = true;
        this.errorMessage = error.message;
      });
    } else {
      Object.keys(this.primaryDetails.controls).forEach(key => {
        if (this.primaryDetails.controls[key].invalid) {
          this.primaryDetails.controls[key].markAsTouched();
        }
      });
    }
  }

}
