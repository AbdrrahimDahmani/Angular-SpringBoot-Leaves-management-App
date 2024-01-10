import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrPendingRequestsComponent } from './hr-pending-requests.component';

describe('HrPendingRequestsComponent', () => {
  let component: HrPendingRequestsComponent;
  let fixture: ComponentFixture<HrPendingRequestsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HrPendingRequestsComponent]
    });
    fixture = TestBed.createComponent(HrPendingRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
