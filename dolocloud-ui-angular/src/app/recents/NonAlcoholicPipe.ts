import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nonalcoholics'
})

export class NonAlcoholicsPipe implements PipeTransform {
  transform(ingredients: any, ...args: any[]): any {
    const nonalcoholics = [];
    for (const ingredient of ingredients) {
      if (ingredient.type !== 'ALCOHOLIC') {
        nonalcoholics.push(ingredient);
      }
    }
    return nonalcoholics;
  }
}
