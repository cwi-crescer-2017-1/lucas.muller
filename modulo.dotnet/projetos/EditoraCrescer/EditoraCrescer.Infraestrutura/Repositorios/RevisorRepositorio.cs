using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class RevisorRepositorio
    {
        private Contexto contexto = new Contexto();

        public List<Revisor> Obter()
        {
            return contexto.Revisores.ToList();
        } 

        public Revisor Obter(int id)
        {
            return contexto.Revisores.FirstOrDefault(x => x.Id == id);
        }

        public Revisor Criar(Revisor revisor)
        {
            var revisorCriado = contexto.Revisores.Add(revisor);
            contexto.SaveChanges();
            return revisorCriado;
        }

        public bool Excluir(int id)
        {
            var revisor = contexto.Revisores.FirstOrDefault(x => x.Id == id);
            if (revisor == null)
            {
                return false;
            }
            else
            {
                contexto.Revisores.Remove(revisor);
                contexto.SaveChanges();
                return true;
            }
        }
    }
}
