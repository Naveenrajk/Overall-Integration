import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Responses } from '../Survey-Model/responses';
import { Survey } from '../Survey-Model/survey';
import { Observable } from 'rxjs';
import { Region } from '../Survey-Model/Region';
import { Department } from '../Survey-Model/Department';
import { Project } from '../Survey-Model/Project';
import { ResponseDetail } from '../Survey-Model/ResponseDetail';
import { Question } from '../Survey-Model/question';
import { Questions } from '../Survey-Model/Questions';
import { Page } from '../Survey-Model/page';

@Injectable({
  providedIn: 'root'
})
export class SurveyresponseService {

  private url: string = "http://localhost:4050";

  constructor(private http: HttpClient) { }

  insertResponses(responses: Responses) {
    return this.http.post(this.url + "/response", responses);
  }

  insertResponseDetails(responseDetail: ResponseDetail) {
    return this.http.post(this.url + "/responsedetail", responseDetail);
  }

  getAllpages(survey: Survey): Observable<any> {
    return this.http.get(this.url + "/getPage/" + survey.surveyId);
  }
  // getAllquestions(survey: Survey): Observable<any> {
  //   return this.http.get(this.url + "/getquestion/" + survey.surveyId);
  // }
  getAllquestions(page:Page):Observable<any> {
    return this.http.get(this.url + "/getquestionpage/"+page.pageId);
  }
  // getAlloptions(survey: Survey): Observable<any> {
  //   return this.http.get(this.url + "/getOption/" + survey.surveyId);
  // }
  getAlloptions(page: Page): Observable<any> {
    return this.http.get(this.url + "/getOptionPage/" + page.pageId);
  }

  getOptions(question: Question): Observable<any> {
    return this.http.get(this.url + "/getOptionQuestion/" + question.questionId);
  }

  getSurvey(survey: Survey): Observable<any> {
    return this.http.get(this.url + "/getsurvey/" + survey.surveyId);
  }

}
