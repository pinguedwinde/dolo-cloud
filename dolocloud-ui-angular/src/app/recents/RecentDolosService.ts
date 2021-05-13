import { Injectable } from '@angular/core';
import { ApiService } from '../api/ApiService';

@Injectable()
export class RecentDolosService {

  constructor(private apiService: ApiService) {
  }

  getRecentDolos() {
    return this.apiService.get('/design/recent');
  }

}
