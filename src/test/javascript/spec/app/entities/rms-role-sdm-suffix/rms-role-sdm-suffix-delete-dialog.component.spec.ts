/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { RmsRoleSdmSuffixDeleteDialogComponent } from 'app/entities/rms-role-sdm-suffix/rms-role-sdm-suffix-delete-dialog.component';
import { RmsRoleSdmSuffixService } from 'app/entities/rms-role-sdm-suffix/rms-role-sdm-suffix.service';

describe('Component Tests', () => {
    describe('RmsRoleSdmSuffix Management Delete Component', () => {
        let comp: RmsRoleSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<RmsRoleSdmSuffixDeleteDialogComponent>;
        let service: RmsRoleSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsRoleSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(RmsRoleSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsRoleSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsRoleSdmSuffixService);
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
