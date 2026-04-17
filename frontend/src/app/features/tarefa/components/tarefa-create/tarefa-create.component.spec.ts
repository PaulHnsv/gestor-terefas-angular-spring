import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarefaCreateComponentTs } from './tarefa-create.component.ts';

describe('TarefaCreateComponentTs', () => {
  let component: TarefaCreateComponentTs;
  let fixture: ComponentFixture<TarefaCreateComponentTs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TarefaCreateComponentTs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaCreateComponentTs);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
