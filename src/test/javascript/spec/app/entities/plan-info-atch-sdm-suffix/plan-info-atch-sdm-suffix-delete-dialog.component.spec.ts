/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoAtchSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix-delete-dialog.component';
import { PlanInfoAtchSdmSuffixService } from 'app/entities/plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoAtchSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoAtchSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoAtchSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoAtchSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoAtchSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoAtchSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoAtchSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoAtchSdmSuffixService);
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
