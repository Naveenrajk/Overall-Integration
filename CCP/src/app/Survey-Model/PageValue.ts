import { NewReference } from "./NewReference";
import { OptionValue } from "./OptionValue";
import { QuestionValue } from "./QuestionValue";

export class PageValue {
    pageTitle!:String;
    surveyId!:number;
    pageNo!:number;
    question:NewReference[]=[];
   
}