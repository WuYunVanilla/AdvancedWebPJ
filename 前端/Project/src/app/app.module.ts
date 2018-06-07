import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { MindmapComponent } from './mindmap/mindmap.component';
import { DetailsComponent } from './details/details.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { FormsModule } from '@angular/forms';
import { HttpRequestService } from './http-request.service';

import { HttpClientModule } from '@angular/common/http';
import {UserService} from './user.service';
import {CourseService} from './course.service';
import { AddCourseComponent } from './add-course/add-course.component';
import { HomeworkComponent } from './homework/homework.component';
import {MindmapService} from './mindmap.service';
import {NodeService} from './node.service';
import { ReleaseMultipleComponent } from './release-multiple/release-multiple.component';
import { ReleaseShortComponent } from './release-short/release-short.component';

@NgModule({
    declarations: [
        AppComponent,
        IndexComponent,
        CoursesComponent,
        MainComponent,
        MindmapComponent,
        LoginComponent,
        RegisterComponent,
        DetailsComponent,
        AddCourseComponent,
        HomeworkComponent,
        ReleaseMultipleComponent,
        ReleaseShortComponent
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
        UserService,
        CourseService,
        MindmapService,
        NodeService
    ],
    bootstrap: [AppComponent],
    entryComponents: [
        LoginComponent,
        RegisterComponent,
        AddCourseComponent,
        ReleaseMultipleComponent,
        ReleaseShortComponent
    ]
})
export class AppModule { }
