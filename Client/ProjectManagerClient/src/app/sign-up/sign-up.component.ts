import { Component, OnInit } from '@angular/core';
//import { Subscription } from 'rxjs';
//import { ActivatedRoute, Router } from '@angular/router';
import { MyAppService } from '../shared/my-app.service';
//import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  //sub: Subscription;
  //user : any = {};

  constructor(
              // private route: ActivatedRoute,
              // private router: Router,
              // private myAppService: MyAppService
            
            ) { }

  ngOnInit() {
  }

// gotoMain(){
//   this.router.navigate(['/main']);
// }

//   save(form: NgForm) {
//     this.myAppService.save(form).subscribe(result => {
//       this.gotoMain();
//     }, error => console.error(error));
//   }


}
