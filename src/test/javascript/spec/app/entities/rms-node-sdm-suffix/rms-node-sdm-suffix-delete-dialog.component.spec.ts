/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { RmsNodeSdmSuffixDeleteDialogComponent } from 'app/entities/rms-node-sdm-suffix/rms-node-sdm-suffix-delete-dialog.component';
import { RmsNodeSdmSuffixService } from 'app/entities/rms-node-sdm-suffix/rms-node-sdm-suffix.service';

describe('Component Tests', () => {
    describe('RmsNodeSdmSuffix Management Delete Component', () => {
        let comp: RmsNodeSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<RmsNodeSdmSuffixDeleteDialogComponent>;
        let service: RmsNodeSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsNodeSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(RmsNodeSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsNodeSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsNodeSdmSuffixService);
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
