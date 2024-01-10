import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrRequestsHistoryComponent } from './hr-requests-history.component';

describe('HrRequestsHistoryComponent', () => {
  let component: HrRequestsHistoryComponent;
  let fixture: ComponentFixture<HrRequestsHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HrRequestsHistoryComponent]
    });
    fixture = TestBed.createComponent(HrRequestsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
