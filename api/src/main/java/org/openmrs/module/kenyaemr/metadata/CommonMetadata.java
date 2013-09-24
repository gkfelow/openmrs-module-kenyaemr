/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.kenyaemr.metadata;

import org.openmrs.PatientIdentifierType.LocationBehavior;
import org.openmrs.customdatatype.datatype.RegexValidatedTextDatatype;
import org.openmrs.module.idgen.validator.LuhnMod25IdentifierValidator;
import org.openmrs.module.kenyacore.metadata.AbstractMetadataProvider;
import org.openmrs.module.kenyacore.metadata.installer.CoreMetadataInstaller;
import org.openmrs.module.kenyaemr.EmrConstants;
import org.openmrs.module.kenyaemr.datatype.FormDatatype;
import org.openmrs.module.kenyaemr.datatype.LocationDatatype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Common metadata provider
 */
@Component("kenyaemr.common.metadata")
public class CommonMetadata extends AbstractMetadataProvider {

	public static final class EncounterType {
		public static final String CONSULTATION = "465a92f2-baf8-42e9-9612-53064be868e8";
		public static final String LAB_RESULTS = "17a381d1-7e29-406a-b782-aa903b963c28";
		public static final String REGISTRATION = "de1f9d67-b73e-4e1b-90d0-036166fc6995";
		public static final String TRIAGE = "d1059fb9-a079-4feb-a749-eedd709ae542";
	}

	public static final class Form {
		public static final String CLINICAL_ENCOUNTER = "e958f902-64df-4819-afd4-7fb061f59308";
		public static final String LAB_RESULTS = "7e603909-9ed5-4d0c-a688-26ecb05d8b6e";
		public static final String OBSTETRIC_HISTORY = "8e4e1abf-7c08-4ba8-b6d8-19a9f1ccb6c9";
		public static final String OTHER_MEDICATIONS = "d4ff8ad1-19f8-484f-9395-04c755de9a47";
		public static final String PROGRESS_NOTE = "0038a296-62f8-4099-80e5-c9ea7590c157";
		public static final String SURGICAL_AND_MEDICAL_HISTORY = "4f3c9bd8-c117-4a5e-a7eb-12a627c29de6";
		public static final String TRIAGE = "37f6bd8d-586a-4169-95fa-5781f987fe62";
	}

	public static final class GlobalProperty {
		public static final String DEFAULT_LOCATION = "8f80f3f7-bdc0-46f5-8f50-54dea2fcbb01";
	}

	public static final class Location {
		public static final String UNKNOWN = "8d6c993e-c2cc-11de-8d13-0010c6dffd0f";
	}

	public static final class LocationAttributeType {
		public static final String MASTER_FACILITY_CODE = "8a845a89-6aa5-4111-81d3-0af31c45c002";
	}

	public static final class OrderType {
		public static final String DRUG = "131168f4-15f5-102d-96e4-000c29c2a5d7";
	}

	public static final class PatientIdentifierType {
		public static final String NATIONAL_ID = "49af6cdc-7968-4abb-bf46-de10d7f4859f";
		public static final String OLD_ID = "8d79403a-c2cc-11de-8d13-0010c6dffd0f";
		public static final String OPENMRS_ID = "dfacd928-0370-4315-99d7-6ec1c9f7ae76";
		public static final String PATIENT_CLINIC_NUMBER = "b4d66522-11fc-45c7-83e3-39a1af21ae0d";
	}

	public static final class PersonAttributeType {
		public static final String NEXT_OF_KIN_ADDRESS = "7cf22bec-d90a-46ad-9f48-035952261294";
		public static final String NEXT_OF_KIN_CONTACT = "342a1d39-c541-4b29-8818-930916f4c2dc";
		public static final String NEXT_OF_KIN_NAME = "830bef6d-b01f-449d-9f8d-ac0fede8dbd3";
		public static final String NEXT_OF_KIN_RELATIONSHIP = "d0aa9fd1-2ac5-45d8-9c5e-4317c622c8f5";
		public static final String SUBCHIEF_NAME = "40fa0c9c-7415-43ff-a4eb-c7c73d7b1a7a";
		public static final String TELEPHONE_CONTACT = "b2c38640-2603-4629-aebd-3b54f33f1e3a";
	}

	public static final class Provider {
		public static final String UNKNOWN = "ae01b8ff-a4cc-4012-bcf7-72359e852e14";
	}

	public static final class VisitAttributeType {
		public static final String SOURCE_FORM = "8bfab185-6947-4958-b7ab-dfafae1a3e3d";
	}

	public static final class VisitType {
		public static final String OUTPATIENT = "3371a4d4-f66f-4454-a86d-92c7b3da990c";
	}

	@Autowired
	private CoreMetadataInstaller installer;

	/**
	 * @see org.openmrs.module.kenyacore.metadata.AbstractMetadataProvider#install()
	 */
	@Override
	public void install() {
		installer.encounterType("Consultation", "Collection of clinical data during the main consultation", EncounterType.CONSULTATION);
		installer.encounterType("Lab Results", "Collection of laboratory results", EncounterType.LAB_RESULTS);
		installer.encounterType("Registration", "Initial data collection for a patient, not specific to any program", EncounterType.REGISTRATION);
		installer.encounterType("Triage", "Collection of limited data prior to a more thorough examination", EncounterType.TRIAGE);

		installer.form("Clinical Encounter", null, EncounterType.CONSULTATION, "1", Form.CLINICAL_ENCOUNTER);
		installer.form("Lab Results", null, EncounterType.LAB_RESULTS, "1", Form.LAB_RESULTS);
		installer.form("Obstetric History", null, EncounterType.REGISTRATION, "1", Form.OBSTETRIC_HISTORY);
		installer.form("Other Medications", "Recording of non-regimen medications", EncounterType.CONSULTATION, "1", Form.OTHER_MEDICATIONS);
		installer.form("Progress Note", "For additional information - mostly complaints and examination findings.", EncounterType.CONSULTATION, "1", Form.PROGRESS_NOTE);
		installer.form("Surgical and Medical History", null, EncounterType.REGISTRATION, "1", Form.SURGICAL_AND_MEDICAL_HISTORY);
		installer.form("Triage", null, EncounterType.TRIAGE, "1", Form.TRIAGE);

		installer.globalProperty(EmrConstants.GP_DEFAULT_LOCATION, "The facility for which this installation is configured",
				LocationDatatype.class, null, null, GlobalProperty.DEFAULT_LOCATION);

		installer.locationAttributeType("Master Facility Code", "Unique facility code allocated by the Ministry of Health",
				RegexValidatedTextDatatype.class, "\\d{5}", 0, 1, LocationAttributeType.MASTER_FACILITY_CODE);

		installer.patientIdentifierType("Old Identification Number", "Identifier given out prior to OpenMRS",
				null, null, null,
				null, false, PatientIdentifierType.OLD_ID);
		installer.patientIdentifierType("OpenMRS ID", "Medical Record Number generated by OpenMRS for every patient",
				null, null, LuhnMod25IdentifierValidator.class,
				LocationBehavior.REQUIRED, true, PatientIdentifierType.OPENMRS_ID);
		installer.patientIdentifierType("Patient Clinic Number", "Assigned to the patient at a clinic service (not globally unique)",
				".{1,15}", "At most 15 characters long", null,
				LocationBehavior.REQUIRED, false, PatientIdentifierType.PATIENT_CLINIC_NUMBER);
		installer.patientIdentifierType("National ID", "Kenyan national identity card number",
				"\\d{5,10}", "Between 5 and 10 consecutive digits", null,
				LocationBehavior.NOT_USED, false, PatientIdentifierType.NATIONAL_ID);

		installer.personAttributeType("Telephone contact", "Telephone number the patient can be contacted at",
				String.class, null, false, 1.0, PersonAttributeType.TELEPHONE_CONTACT);
		installer.personAttributeType("Subchief name", "Name of subchief or chief of patient's area",
				String.class, null, false, 2.0, PersonAttributeType.SUBCHIEF_NAME);
		installer.personAttributeType("Next of kin name", "Name of patient's next of kin",
				String.class, null, false, 3.0, PersonAttributeType.NEXT_OF_KIN_NAME);
		installer.personAttributeType("Next of kin relationship", "Next of kin relationship to the patient",
				String.class, null, false, 3.1, PersonAttributeType.NEXT_OF_KIN_RELATIONSHIP);
		installer.personAttributeType("Next of kin contact", "Telephone contact of patient's next of kin",
				String.class, null, false, 3.2, PersonAttributeType.NEXT_OF_KIN_CONTACT);
		installer.personAttributeType("Next of kin address", "Address of patient's next of kin",
				String.class, null, false, 3.3, PersonAttributeType.NEXT_OF_KIN_ADDRESS);

		installer.visitAttributeType("Source form", "The form whose submission created the visit",
				FormDatatype.class, null, 0, 1, VisitAttributeType.SOURCE_FORM);

		installer.visitType("Outpatient", "Visit where the patient is not admitted to the hospital", VisitType.OUTPATIENT);
	}
}