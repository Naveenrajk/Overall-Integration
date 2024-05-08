import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Question } from '../Survey-Model/question';
import { Survey } from '../Survey-Model/survey';
import { Responses } from '../Survey-Model/responses';
import { Page } from '../Survey-Model/page';
import { Option } from '../Survey-Model/option';
import { Options } from '../Survey-Model/Options';
import { Questions } from '../Survey-Model/Questions';

@Injectable({
  providedIn: 'root'
})
export class SummaryService {

  private url: string = "http://localhost:4050";

  constructor(private http: HttpClient) { }

  getSurveyDetail(survey:Survey){
    return this.http.get<any>(this.url + "/getsurvey/"+survey.surveyId)
  }

  getQuestionCount(survey:Survey){
    return this.http.get<number>(this.url + "/questionCount/"+survey.surveyId);
  }


  updatePage(page : Page){
    return this.http.put(this.url + "/page",page);
  }

  updateQuestion(question : Question){
    return this.http.put(this.url + "/question",question);
  }

  updateOption(option : Options){
    return this.http.put(this.url + "/option",option);
  }

  getPagesCount(survey:Survey){
    return this.http.get<number>(this.url + "/pageCount/"+survey.surveyId);
  }

  getResponseDetailCount(survey:Survey){
    return this.http.get<number>(this.url + "/responseDetailCount/"+survey.surveyId);
  }

  getAllquestions(survey:Survey){
    return this.http.get<Questions[]>(this.url+"/getquestion/"+survey.surveyId);
  }

  getAlloptions(survey:Survey){
    return this.http.get<Options[]>(this.url+"/getOption/"+survey.surveyId);
  }

  getAllpages(survey:Survey){
    return this.http.get<Page[]>(this.url+"/getPage/"+survey.surveyId);
  }

  getAllResponseDetails(survey:Survey){
    return this.http.get<Responses[]>(this.url+"/getresponses/"+survey.surveyId);
  }
}
