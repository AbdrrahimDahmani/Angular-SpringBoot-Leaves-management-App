import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrRequestsUpdaterComponent } from './hr-requests-updater.component';

describe('HrRequestsUpdaterComponent', () => {
  let component: HrRequestsUpdaterComponent;
  let fixture: ComponentFixture<HrRequestsUpdaterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HrRequestsUpdaterComponent]
    });
    fixture = TestBed.createComponent(HrRequestsUpdaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
