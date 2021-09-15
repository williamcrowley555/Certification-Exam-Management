/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dto.DoanDTO;
import com.certification_exam.dto.ThongKeDoanDTO;
import com.certification_exam.mapper.impl.DoanMapper;
import com.certification_exam.mapper.impl.ThongKeDoanMapper;
import java.util.List;
import com.certification_exam.dal.IDoanDAL;
import com.certification_exam.dal.IThongKeDoanDAL;

/**
 *
 * @author kossp
 */
public class ThongKeDoanDAL extends AbstractDAL<ThongKeDoanDTO> implements IThongKeDoanDAL {

    @Override
    public List<ThongKeDoanDTO> findByIdTour(Long id) {
        String sql = "SELECT DISTINCT d.id, d.ten_doan, COUNT(DISTINCT dskd.id_khach) AS so_khach, d.gia_tien,\n" +
                    "(COUNT(DISTINCT dskd.id_khach) * d.gia_tien) AS doanh_thu,\n" +
                    "SUM(DISTINCT cpd.chi_phi) AS tong_chi_phi, ((COUNT(DISTINCT dskd.id_khach) * d.gia_tien) - SUM(DISTINCT cpd.chi_phi)) AS lai\n" +
                    "FROM (ds_khach_doan AS dskd RIGHT JOIN doan AS d ON dskd.id_doan = d.id)\n" +
                    "LEFT JOIN chi_phi_doan AS cpd ON d.id = cpd.id_doan\n" +
                    "WHERE d.id_tour = ? \n" +
                    "GROUP BY d.id, d.ten_doan, d.gia_tien\n" +
                    "ORDER BY d.id";
        return query(sql, new ThongKeDoanMapper(), id);
    }
}
