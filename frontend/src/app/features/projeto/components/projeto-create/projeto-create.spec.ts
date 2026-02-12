import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetoCreate } from './projeto-create';

describe('ProjetoCreate', () => {
  let component: ProjetoCreate;
  let fixture: ComponentFixture<ProjetoCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjetoCreate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjetoCreate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
