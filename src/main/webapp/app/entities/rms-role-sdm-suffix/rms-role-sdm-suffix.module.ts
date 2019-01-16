import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    RmsRoleSdmSuffixComponent,
    RmsRoleSdmSuffixDetailComponent,
    RmsRoleSdmSuffixUpdateComponent,
    RmsRoleSdmSuffixDeletePopupComponent,
    RmsRoleSdmSuffixDeleteDialogComponent,
    rmsRoleRoute,
    rmsRolePopupRoute
} from './';

const ENTITY_STATES = [...rmsRoleRoute, ...rmsRolePopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RmsRoleSdmSuffixComponent,
        RmsRoleSdmSuffixDetailComponent,
        RmsRoleSdmSuffixUpdateComponent,
        RmsRoleSdmSuffixDeleteDialogComponent,
        RmsRoleSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        RmsRoleSdmSuffixComponent,
        RmsRoleSdmSuffixUpdateComponent,
        RmsRoleSdmSuffixDeleteDialogComponent,
        RmsRoleSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbRmsRoleSdmSuffixModule {}
