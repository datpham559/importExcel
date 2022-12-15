package importExcel.helper;

import java.util.List;

public class ScaleConfig {
    public static List<String> scales = List.of("Công ty", "Chi nhánh", "Viện", "Chương trình", "Trường", "Cty", "Cổ phần"
            , "TNHH", "CN", "Quỹ", "Tỉnh", "Huyện", "Xã", "Trung tâm", "Liên đoàn", "NCC", "Văn phòng", "Bộ", "Ban quản trị"
            , "Tập đoàn","Kho");

    public static List<String> header_CCDC = List.of("Vào sổ","Mã CCDC(*)" , "Tên CCDC(*)", "Ngày ghi tăng (*)",
            "Đơn vị tính", "Tổng số lượng","Đơn giá","Tổng giá trị","Số kỳ phân bổ (*)", "Số kỳ PB còn lại",
            "Giá trị đã PB","Giá trị còn lại","Giá trị phân bổ/kỳ","TK chờ PB","Loại đối tượng PB(*)","Đối tượng PB(*)",
            "Số lượng","Tỷ lệ PB","TK chi phí","Khoản mục chi phí");

    public static List<String> header_TSCD = List.of("Vào sổ", "Mã TSCĐ(*)","Tên TSCĐ(*)","Phòng ban(*)","Loại(*)",
            "Ngày ghi tăng(*)","Ngày bđ tính khấu hao(*)","Thời gian sử dụng","Tháng/Năm TGSD","Thời gian SD còn lại",
            "Tháng/Năm TGSDCL","Nguyên giá","Giá trị tính KH","Hao mòn lũy kế","Giá trị còn lại","Giá trị KH tháng","TK nguyên giá(*)",
            "TK chi phí(*)","TK khấu hao(*)","ĐT tập hợp CP","Mục thu-chi","Mã thống kê","Loại đối tượng PV(*)","Đối tượng PB(*)",
            "Tỷ lệ PB","TK phân bổ chi phí","Khoản mục chi phí");


}
