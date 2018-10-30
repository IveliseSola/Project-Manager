import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatIconModule, MatSidenavModule, MatDialogModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { MyNavComponent } from './my-nav/my-nav.component';
import { LayoutModule } from '@angular/cdk/layout';

const appRoutes: Routes = [
  
    { path: '', redirectTo: '/sign-up', pathMatch: 'full' },
    {
      path:'sign-up',
      component: SignUpComponent
    },
    
  ];

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    MyNavComponent
  ],
  imports: [
    BrowserModule,
    MatButtonModule, 
    MatCardModule, 
    MatInputModule, 
    MatListModule, 
    MatToolbarModule,
    MatIconModule,
    MatDialogModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    LayoutModule,
    MatSidenavModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
