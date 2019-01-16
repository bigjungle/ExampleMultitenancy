import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepDataAtchSdmSuffixComponent,
    PlanInfoStepDataAtchSdmSuffixDetailComponent,
    PlanInfoStepDataAtchSdmSuffixUpdateComponent,
    PlanInfoStepDataAtchSdmSuffixDeletePopupComponent,
    PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent,
    planInfoStepDataAtchRoute,
    planInfoStepDataAtchPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepDataAtchRoute, ...planInfoStepDataAtchPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepDataAtchSdmSuffixComponent,
        PlanInfoStepDataAtchSdmSuffixDetailComponent,
        PlanInfoStepDataAtchSdmSuffixUpdateComponent,
        PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataAtchSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepDataAtchSdmSuffixComponent,
        PlanInfoStepDataAtchSdmSuffixUpdateComponent,
        PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent,
        PlanInfoStepDataAtchSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepDataAtchSdmSuffixModule {}
