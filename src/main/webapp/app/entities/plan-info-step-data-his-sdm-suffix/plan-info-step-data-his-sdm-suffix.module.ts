import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepDataHisSdmSuffixComponent,
    PlanInfoStepDataHisSdmSuffixDetailComponent,
    PlanInfoStepDataHisSdmSuffixUpdateComponent,
    PlanInfoStepDataHisSdmSuffixDeletePopupComponent,
    PlanInfoStepDataHisSdmSuffixDeleteDialogComponent,
    planInfoStepDataHisRoute,
    planInfoStepDataHisPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepDataHisRoute, ...planInfoStepDataHisPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepDataHisSdmSuffixComponent,
        PlanInfoStepDataHisSdmSuffixDetailComponent,
        PlanInfoStepDataHisSdmSuffixUpdateComponent,
        PlanInfoStepDataHisSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataHisSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepDataHisSdmSuffixComponent,
        PlanInfoStepDataHisSdmSuffixUpdateComponent,
        PlanInfoStepDataHisSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataHisSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepDataHisSdmSuffixModule {}
