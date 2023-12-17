import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CutestCatComponent } from './cutest-cat.component';

describe('CutestCatComponent', () => {
  let component: CutestCatComponent;
  let fixture: ComponentFixture<CutestCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CutestCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CutestCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
