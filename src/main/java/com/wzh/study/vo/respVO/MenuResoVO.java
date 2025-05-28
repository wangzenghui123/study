package com.wzh.study.vo.respVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResoVO implements Comparable<MenuResoVO>{

    public boolean isParent = true;
    private String id;

    private String name;

    private String perms;

    private String url;

    private String pid;

    private Integer orderNum;

    private Integer type;

    private String pidName;



    private ArrayList<MenuResoVO> children;

    @Override
    public int compareTo(MenuResoVO o) {
        return Integer.compare(this.orderNum,o.orderNum);
    }
}
