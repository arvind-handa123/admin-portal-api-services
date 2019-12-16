package co.yabx.admin.portal.app.enums;

public enum EducationalQualification {

	BAMS(25, "BAMS"), BBA(22, "BBA"), BCA(13, "BCA"), BDS(26, "BDS"), BFA(42, "BFA"), BHM(23, "BHM"), BHMS(27, "BHMS"),
	BJMC(43, "BJMC"), BL_LLB(38, "BL/LLB"), BPT(28, "BPT"), BVSC_(29, "BVSc."), B_A(40, "B.A"), B_ARCH(3, "B.Arch"),
	B_COM(16, "B.Com"), B_DES(4, "B.Des"), B_E(5, "B.E/B.Tech"), B_ED(41, "B.Ed"), B_IT(12, "B.IT"),
	B_PHARMA(6, "B.Pharma"), B_SC(56, "B.Sc"), CA(17, "CA"), CFA(18, "CFA"), CS(19, "CS"), DIPLOMA(54, "Diploma"),
	DM(30, "DM"), HIGH_SCHOOL(52, "High School"), ICWA(20, "ICWA"), MBA(24, "MBA/PGDM"), MBBS(33, "MBBS"),
	MCA(14, "MCA"), MCH(34, "MCh"), MDS(35, "MDS"), MFA(47, "MFA"), MJMC(48, "MJMC"), ML_LLM(39, "ML/LLM"),
	MPT(36, "MPT"), MSW(49, "MSW"), MVSC_(37, "MVSc."), M_A(44, "M.A"), M_ARCH(7, "M.Arch"), M_COM(21, "M.Com"),
	M_D(31, "M.D."), M_DES(8, "M.Des"), M_E(9, "M.E/M.Tech"), M_ED(45, "M.Ed"), M_PHARMA(10, "M.Pharma"),
	M_PHIL(50, "M.Phil"), M_SC(46, "M.Sc"), M_S_E(11, "M.S. (Engineering)"), M_S_M(32, "M.S. (Medicine)"),
	PGDCA(15, "PGDCA"), PH_D(51, "Ph.D"), TRADE_SCHOOL(53, "Trade School"), OTHERS(55, "Others");

	private int id;
	private String name;

	EducationalQualification(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static EducationalQualification findById(int id) {
		EducationalQualification[] customEducations = values();
		for (EducationalQualification customEducation : customEducations) {
			if (customEducation.getId() == id) {
				return customEducation;
			}
		}
		return null;
	}

	public static EducationalQualification findByValue(String value) {
		EducationalQualification[] customEducations = values();
		for (EducationalQualification customEducation : customEducations) {
			if (customEducation.getName().equalsIgnoreCase(value)) {
				return customEducation;
			}
		}
		return null;
	}
}
