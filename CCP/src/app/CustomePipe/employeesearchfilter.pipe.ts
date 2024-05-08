import { Pipe, PipeTransform } from '@angular/core';
import { SortbyPipe } from './pollsearchfilter.pipe';

@Pipe({
  name: 'employeesearchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if(!value) return null;
    if(!args) return value;


  const searchTerm = args.toLowerCase(); // Normalize search term to lowercase

    return value.filter((item: any) => {

      console.log(args);
      console.log((item))
        // Filter items checking if any string property starts with searchTerm
        for (let key in item) {
            if (typeof item[key] === 'string' && item[key].toLowerCase().startsWith(searchTerm)) {
                return true;
            }
        }
        return false;
    });
  }
}
