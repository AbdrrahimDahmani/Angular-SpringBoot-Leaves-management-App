import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { LeaveRequestComponent } from './Components/leave-request/leave-request.component';
import { LeaveHistoryComponent } from './Components/leave-history/leave-history.component';
import { MyRequestsComponent } from './Components/my-requests/my-requests.component';
import { WelcomeComponent } from './Components/welcome/welcome.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './Components/user-profile/dashboard.component';
import { SidebarComponent } from './Components/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { JwtModule } from '@auth0/angular-jwt';
import { jwtConfig } from './jwt-config';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HrSidebarComponent } from './Components/human-resource/hr-sidebar/hr-sidebar.component';
import { HrDashboardComponent } from './Components/human-resource/hr-dashboard/hr-dashboard.component';
import { HrPendingRequestsComponent } from './Components/human-resource/hr-pending-requests/hr-pending-requests.component';
import { HrRequestsHistoryComponent } from './Components/human-resource/hr-requests-history/hr-requests-history.component';
import { HrRequestsUpdaterComponent } from './Components/human-resource/hr-requests-updater/hr-requests-updater.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    LeaveRequestComponent,
    MyRequestsComponent,
    WelcomeComponent,
    DashboardComponent,
    LeaveHistoryComponent,
    SidebarComponent,
    HrSidebarComponent,
    HrDashboardComponent,
    HrPendingRequestsComponent,
    HrRequestsHistoryComponent,
    HrRequestsUpdaterComponent, // Ajoutez une virgule ici
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    JwtModule.forRoot({
      config: jwtConfig,
    }),
    MatFormFieldModule,
    MatIconModule,
    MatSnackBarModule,
    MatPaginatorModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent],
})
export class AppModule {}
