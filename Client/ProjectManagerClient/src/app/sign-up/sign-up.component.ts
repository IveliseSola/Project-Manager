import { Component, OnInit, Inject } from '@angular/core';
//import { Subscription } from 'rxjs';
//import { ActivatedRoute, Router } from '@angular/router';
import { MyAppService } from '../shared/my-app.service';
//import { NgForm } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})

export class SignUpComponent {

  constructor(
    public dialogRef: MatDialogRef<SignUpComponent>,
    @Inject(MAT_DIALOG_DATA) public user: any) {}

  close(): void {
    this.dialogRef.close();
  }
}
