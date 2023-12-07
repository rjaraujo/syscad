package cadastro.controller;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cadastro.model.Perfil;

public class PerfilConverter implements Converter {
	
	private List<Perfil> listaPerfis;
	
    public PerfilConverter(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        
        Long id = Long.valueOf(value);
        
        for (Perfil perfil: listaPerfis) {
            if (id.equals(perfil.getId())) {
                return perfil;
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        
        Perfil perfil = (Perfil) value;
        
        return perfil.getId().toString();
    }
}
