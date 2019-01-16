/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { ParaPropSdmSuffixDeleteDialogComponent } from 'app/entities/para-prop-sdm-suffix/para-prop-sdm-suffix-delete-dialog.component';
import { ParaPropSdmSuffixService } from 'app/entities/para-prop-sdm-suffix/para-prop-sdm-suffix.service';

describe('Component Tests', () => {
    describe('ParaPropSdmSuffix Management Delete Component', () => {
        let comp: ParaPropSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ParaPropSdmSuffixDeleteDialogComponent>;
        let service: ParaPropSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaPropSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(ParaPropSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaPropSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaPropSdmSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
