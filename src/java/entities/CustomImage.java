/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name="customImage",schema="bloomingdb")
public class CustomImage extends Content {

    private static final long serialVersionUID = 1L;
    private Byte[] bytes;

}
