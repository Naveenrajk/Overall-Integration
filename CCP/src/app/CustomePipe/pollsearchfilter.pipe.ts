import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'pollsearchfilter'
})
export class SortbyPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if(!value) return null;
    if(!args) return value;
  args = args.toLowerCase();
    

    return value.filter(function(item: string){
      console.log(item);
      console.log(args);
      console.log(JSON.stringify(item))
      console.log(JSON.stringify(item).toLowerCase().includes(args))
      return JSON.stringify(item).toLowerCase().includes(args);
    })
  }

  // const searchTerm = args.toLowerCase(); // Normalize search term to lowercase
  //   return value.filter((item: any) => {
  //       // Filter items checking if any string property starts with searchTerm
  //       for (let key in item) {
  //           if (typeof item[key] === 'string' && item[key].toLowerCase().startsWith(searchTerm)) {
  //               return true;
  //           }
  //       }
  //       return false;
  //   });
  // }

}
