package cadastro.controller;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cadastro.model.Endereco;

public class EnderecoConverter implements Converter {
	
	private List<List<Endereco>> listaEnds;
	
    public EnderecoConverter(List<List<Endereco>> list) {
        this.listaEnds = list;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        
        Long id = Long.valueOf(value);
        
        for (List<Endereco> endereco: listaEnds) {
            if (id.equals(((UIComponent) endereco).getId())) {
                return endereco;
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        
        Endereco endereco = (Endereco) value;
        
        return endereco.getId().toString();
    }
}
