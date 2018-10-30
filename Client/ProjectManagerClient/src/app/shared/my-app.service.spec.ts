import { TestBed } from '@angular/core/testing';

import { MyAppService } from './my-app.service';

describe('MyAppService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MyAppService = TestBed.get(MyAppService);
    expect(service).toBeTruthy();
  });
});
