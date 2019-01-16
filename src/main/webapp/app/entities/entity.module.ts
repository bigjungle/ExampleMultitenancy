import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PlandbPlanInfoSdmSuffixModule } from './plan-info-sdm-suffix/plan-info-sdm-suffix.module';
import { PlandbPlanInfoStepSdmSuffixModule } from './plan-info-step-sdm-suffix/plan-info-step-sdm-suffix.module';
import { PlandbPlanInfoAtchSdmSuffixModule } from './plan-info-atch-sdm-suffix/plan-info-atch-sdm-suffix.module';
import { PlandbPlanInfoStepAtchSdmSuffixModule } from './plan-info-step-atch-sdm-suffix/plan-info-step-atch-sdm-suffix.module';
import { PlandbPlanInfoDataSdmSuffixModule } from './plan-info-data-sdm-suffix/plan-info-data-sdm-suffix.module';
import { PlandbPlanInfoStepDataSdmSuffixModule } from './plan-info-step-data-sdm-suffix/plan-info-step-data-sdm-suffix.module';
import { PlandbPlanInfoDataAtchSdmSuffixModule } from './plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix.module';
import { PlandbPlanInfoStepDataAtchSdmSuffixModule } from './plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix.module';
import { PlandbPlanInfoDataHisSdmSuffixModule } from './plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix.module';
import { PlandbPlanInfoStepDataHisSdmSuffixModule } from './plan-info-step-data-his-sdm-suffix/plan-info-step-data-his-sdm-suffix.module';
import { PlandbPlanInfoDataAtchHisSdmSuffixModule } from './plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix.module';
import { PlandbPlanInfoStepDataAtchHisSdmSuffixModule } from './plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix.module';
import { PlandbVerifyRecSdmSuffixModule } from './verify-rec-sdm-suffix/verify-rec-sdm-suffix.module';
import { PlandbParaTypeSdmSuffixModule } from './para-type-sdm-suffix/para-type-sdm-suffix.module';
import { PlandbParaClassSdmSuffixModule } from './para-class-sdm-suffix/para-class-sdm-suffix.module';
import { PlandbParaCatSdmSuffixModule } from './para-cat-sdm-suffix/para-cat-sdm-suffix.module';
import { PlandbParaStateSdmSuffixModule } from './para-state-sdm-suffix/para-state-sdm-suffix.module';
import { PlandbParaSourceSdmSuffixModule } from './para-source-sdm-suffix/para-source-sdm-suffix.module';
import { PlandbParaPropSdmSuffixModule } from './para-prop-sdm-suffix/para-prop-sdm-suffix.module';
import { PlandbParaAttrSdmSuffixModule } from './para-attr-sdm-suffix/para-attr-sdm-suffix.module';
import { PlandbRmsUserSdmSuffixModule } from './rms-user-sdm-suffix/rms-user-sdm-suffix.module';
import { PlandbRmsPersonSdmSuffixModule } from './rms-person-sdm-suffix/rms-person-sdm-suffix.module';
import { PlandbRmsDepSdmSuffixModule } from './rms-dep-sdm-suffix/rms-dep-sdm-suffix.module';
import { PlandbRmsRoleSdmSuffixModule } from './rms-role-sdm-suffix/rms-role-sdm-suffix.module';
import { PlandbRmsNodeSdmSuffixModule } from './rms-node-sdm-suffix/rms-node-sdm-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        PlandbPlanInfoSdmSuffixModule,
        PlandbPlanInfoStepSdmSuffixModule,
        PlandbPlanInfoAtchSdmSuffixModule,
        PlandbPlanInfoStepAtchSdmSuffixModule,
        PlandbPlanInfoDataSdmSuffixModule,
        PlandbPlanInfoStepDataSdmSuffixModule,
        PlandbPlanInfoDataAtchSdmSuffixModule,
        PlandbPlanInfoStepDataAtchSdmSuffixModule,
        PlandbPlanInfoDataHisSdmSuffixModule,
        PlandbPlanInfoStepDataHisSdmSuffixModule,
        PlandbPlanInfoDataAtchHisSdmSuffixModule,
        PlandbPlanInfoStepDataAtchHisSdmSuffixModule,
        PlandbVerifyRecSdmSuffixModule,
        PlandbParaTypeSdmSuffixModule,
        PlandbParaClassSdmSuffixModule,
        PlandbParaCatSdmSuffixModule,
        PlandbParaStateSdmSuffixModule,
        PlandbParaSourceSdmSuffixModule,
        PlandbParaPropSdmSuffixModule,
        PlandbParaAttrSdmSuffixModule,
        PlandbRmsUserSdmSuffixModule,
        PlandbRmsPersonSdmSuffixModule,
        PlandbRmsDepSdmSuffixModule,
        PlandbRmsRoleSdmSuffixModule,
        PlandbRmsNodeSdmSuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbEntityModule {}
