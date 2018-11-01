import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { SignUpComponent } from '../sign-up/sign-up.component';


export interface DialogData {
  username: String,
  password: String
}

@Component({
  selector: 'app-my-nav',
  templateUrl: './my-nav.component.html',
  styleUrls: ['./my-nav.component.css'],
})
export class MyNavComponent {

user: any = {}

  constructor(public dialog: MatDialog){}

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    let dialogRef = this.dialog.open(SignUpComponent, dialogConfig);
  
    dialogRef.afterClosed().subscribe(result => {
      // this.user.username = result.username;
      // this.user.password = result.password;
      console.log(`this is the value: ${result}`);
      
    });
  
  }
}
