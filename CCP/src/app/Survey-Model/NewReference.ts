import { OptionValue } from "./OptionValue";
import { Reference } from "./Reference";
import { Option } from "./option";

export class NewReference {
    questions!:String;
    pageTitle!:String;
    pageNo!:number;
    questionNo!:number;
    optionType!:String;
    option!:OptionValue[];
    mandatory!:boolean;
}