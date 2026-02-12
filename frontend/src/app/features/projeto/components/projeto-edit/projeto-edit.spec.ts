import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetoEdit } from './projeto-edit';

describe('ProjetoEdit', () => {
  let component: ProjetoEdit;
  let fixture: ComponentFixture<ProjetoEdit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjetoEdit]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjetoEdit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
