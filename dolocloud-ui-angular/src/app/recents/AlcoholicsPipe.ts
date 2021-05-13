import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'alcoholics'
})

export class AlcoholicsPipe implements PipeTransform {
  transform(ingredients: any, ...args: any[]): any {
    const alcoholics = [];
    for (const ingredient of ingredients) {
      if (ingredient.type === 'ALCOHOLIC') {
        const alcoholic: any = {};
        alcoholic.id = ingredient.id;
        alcoholic.name = 'a ' + ingredient.name;
        alcoholic.type = ingredient.type;
        alcoholics.push(alcoholic);
      }
    }
    return alcoholics;
  }
}
