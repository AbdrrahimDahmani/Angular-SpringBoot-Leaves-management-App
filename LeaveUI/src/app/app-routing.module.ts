import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { DashboardComponent } from './Components/user-profile/dashboard.component';
import { LeaveRequestComponent } from './Components/leave-request/leave-request.component';
import { LeaveHistoryComponent } from './Components/leave-history/leave-history.component';
import { MyRequestsComponent } from './Components/my-requests/my-requests.component';
import { WelcomeComponent } from './Components/welcome/welcome.component';
import { authGuard } from './guard/auth.guard';
import { hrAuthGuard } from './guard/hr-auth.guard';
import { HrDashboardComponent } from './Components/human-resource/hr-dashboard/hr-dashboard.component';
import { HrPendingRequestsComponent } from './Components/human-resource/hr-pending-requests/hr-pending-requests.component';
import { HrRequestsHistoryComponent } from './Components/human-resource/hr-requests-history/hr-requests-history.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: WelcomeComponent },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  { path: 'leave', component: LeaveRequestComponent, canActivate: [authGuard] },
  {
    path: 'leaveHistory',
    component: LeaveHistoryComponent,
    canActivate: [authGuard],
  },
  {
    path: 'myrequests',
    component: MyRequestsComponent,
    canActivate: [authGuard],
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'hr-dashboard',
    component: HrDashboardComponent,
    canActivate: [hrAuthGuard],
  },
  {
    path: 'pending-requests',
    component: HrPendingRequestsComponent,
    canActivate: [hrAuthGuard],
  },
  {
    path: 'requests-history',
    component: HrRequestsHistoryComponent,
    canActivate: [hrAuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
