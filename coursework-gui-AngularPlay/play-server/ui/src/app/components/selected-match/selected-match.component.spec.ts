import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedMatchComponent } from './selected-match.component';

describe('SelectedMatchComponent', () => {
  let component: SelectedMatchComponent;
  let fixture: ComponentFixture<SelectedMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectedMatchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
