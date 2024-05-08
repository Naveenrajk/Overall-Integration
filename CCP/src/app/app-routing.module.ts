import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { LoginComponent } from './login/login.component';
import { RegionHomeComponent } from './region-home/region-home.component';
import { DepartmentHomeComponent } from './department-home/department-home.component';
import { ProjectHomeComponent } from './project-home/project-home.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';
import { ViewregionComponent } from './viewregion/viewregion.component';
import { UpdateRegionComponent } from './update-region/update-region.component';
import { TwoFactorComponent } from './two-factor/two-factor.component';
import { EmployeePageComponent } from './employee-page/employee-page.component';
import { PostComponent } from './post/post.component';
import { UserPageComponent } from './user-page/user-page.component';
import { PollComponent } from './poll/poll.component';
import { SurveyComponent } from './survey/survey.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ResetComponent } from './reset/reset.component';
import { PasswordresetComponent } from './passwordreset/passwordreset.component';
import { CreateComponent } from './create/create.component';
import { ViewComponent } from './view/view.component';
import { DraftComponent } from './draft/draft.component';
import { EmployeeComponent } from './employee/employee.component';
import { InviteEmployeeComponent } from './invite-employee/invite-employee.component';
import { ForcepasswordresetComponent } from './forcepasswordreset/forcepasswordreset.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { ForgotPasswordotpComponent } from './forgot-passwordotp/forgot-passwordotp.component';
import { ForgotresetpasswordComponent } from './forgotresetpassword/forgotresetpassword.component';
import { UsernavbarComponent } from './usernavbar/usernavbar.component';
import { AuthGuard } from './JwtUtil/auth-guard.service';
import { UserregisterComponent } from './userregister/userregister.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { CreationPageComponent } from './creation-page/creation-page.component';
import { SurveyBuilderComponent } from './survey-builder/survey-builder.component';
import { ResponseTemplateComponent } from './response-template/response-template.component';
import { SummaryPageComponent } from './summary-page/summary-page.component';
import { SurveyResponseComponent } from './survey-response/survey-response.component';
import { ResponseSummaryComponent } from './response-summary/response-summary.component';
import { AllSurveyComponent } from './all-survey/all-survey.component';


const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'Reset',component:ResetComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'navbar',component:NavbarComponent,canActivate:[AuthGuard]},
  {path:'usernavbar',component:UsernavbarComponent,canActivate:[AuthGuard]},

  {path:'Admin',component:AdminPageComponent,canActivate:[AuthGuard], data: { allowedRoles: ['admin']}},
  {path:'RegionHome',component:RegionHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'Admin/DepartmentHome',component:DepartmentHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'Admin/ProjectHome',component:ProjectHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'Admin/EmployeeHome',component:EmployeeHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'ViewRegion',component:ViewregionComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin'] }},
  {path:'UpdateRegion/:id',component:UpdateRegionComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'TwoFactor',component:TwoFactorComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'employeePage',component:EmployeePageComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'post',component:PostComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'poll',component:PollComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  // {path:'survey',component:SurveyComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'User',component:UserPageComponent,canActivate:[AuthGuard],data: { allowedRoles: ['employee'] }},
  {path:'PasswordReset',component:PasswordresetComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'employeepage',component:EmployeePageComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin'] }},
  {path:'inviteEmployee',component:InviteEmployeeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  
  {path:'departmenthome',component:DepartmentHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin'] }},
  {path :'forcepasswordreset',component:ForcepasswordresetComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},

  {path:'projecthome',component:ProjectHomeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin'] }},

  {path :'userregister',component:UserregisterComponent},

  {path:'forgotpassword',component:ForgotpasswordComponent},
 
  {path:'forgotpasswordotp',component:ForgotPasswordotpComponent},
  {path:'forgotresetpassword',component:ForgotresetpasswordComponent},
  { path: 'survey/create', component: CreationPageComponent,canActivate:[AuthGuard] ,data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'survey/builder', component: SurveyBuilderComponent,canActivate:[AuthGuard] ,data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'survey/summary', component: SummaryPageComponent,canActivate:[AuthGuard] ,data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'survey/summary/:id', component: SummaryPageComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] } },
  { path: 'survey/response', component: ResponseTemplateComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] } },
  { path: 'survey/userResponse/:id', component: SurveyResponseComponent,canActivate:[AuthGuard] ,data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'survey/userResponse', component: SurveyResponseComponent,canActivate:[AuthGuard] ,data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'survey/responseDetail', component: ResponseSummaryComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] } },
  { path: 'survey/responseDetail/:id', component: ResponseSummaryComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] } },
  { path: 'allsurvey', component: AllSurveyComponent },

  { path: 'create', component: CreateComponent ,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  { path: 'view', component: ViewComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'viewEmployee',component:EmployeeComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'draft', component:DraftComponent,canActivate:[AuthGuard],data: { allowedRoles: ['admin', 'employee'] }},
  {path:'unauthorized',component:UnauthorizedComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
