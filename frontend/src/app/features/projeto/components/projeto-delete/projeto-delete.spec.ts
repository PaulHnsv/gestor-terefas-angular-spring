import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetoDelete } from './projeto-delete';

describe('ProjetoDelete', () => {
  let component: ProjetoDelete;
  let fixture: ComponentFixture<ProjetoDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjetoDelete]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjetoDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
