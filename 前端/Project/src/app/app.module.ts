import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { MenuComponent } from './menu/menu.component';
import { MindmapComponent } from './mindmap/mindmap.component';
import { DetailsComponent } from './details/details.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { FormsModule } from '@angular/forms';
import { HttpRequestService } from './http-request.service';

import { HttpClientModule } from '@angular/common/http';
import {UserService} from './user.service';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    CoursesComponent,
    MainComponent,
    MenuComponent,
    MindmapComponent,
    LoginComponent,
    RegisterComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    FormsModule,
    HttpClientModule
  ],
  providers: [
    HttpRequestService,
    UserService
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    LoginComponent,
    RegisterComponent
  ]
})
export class AppModule { }
