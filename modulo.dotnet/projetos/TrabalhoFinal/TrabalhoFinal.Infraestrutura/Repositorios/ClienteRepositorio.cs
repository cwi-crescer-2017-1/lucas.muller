using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;
using System.Text;
using System.Threading.Tasks;
using TrabalhoFinal.Dominio;
using System.Text.RegularExpressions;

namespace TrabalhoFinal.Infraestrutura.Repositorios
{
    public class ClienteRepositorio : IDisposable
    {
        private Contexto contexto = new Contexto();

        public List<Cliente> Obter()
        {
            return contexto.Clientes.ToList();
        }

        public Cliente Obter(int id)
        {
            return contexto.Clientes.Where(x => x.Id == id).FirstOrDefault();
        }

        public List<Cliente> Obter(string nome)
        {
            return contexto.Clientes.Where(x => x.Nome.Contains(nome)).ToList();
        }

        public List<Locacao> ObterLocacoes(int id)
        {
            return contexto.Locacoes
                .Include(x => x.Pacote)
                .Include(x => x.Produto)
                .Include(x => x.Opcionais)
                .Include(x => x.Cliente)
                .Where(x => x.IdCliente == id)
                .OrderByDescending(x => x.DataDevolucaoPrevista).ToList();
        }

        public Cliente Criar(Cliente cliente)
        {
            contexto.Clientes.Add(cliente);
            contexto.SaveChanges();
            return cliente;
        }

        public bool Alterar(Cliente cliente)
        {
            if (VerificaExistencia(cliente.Id) == false)
                return false;

            contexto.Entry(cliente).State = System.Data.Entity.EntityState.Modified;
            contexto.SaveChanges();
            return true;
        }

        public bool Excluir(int id)
        {
            Cliente cliente = Obter(id);
            if (cliente == null)
                return false;

            contexto.Clientes.Remove(cliente);
            contexto.SaveChanges();
            return true;
        }

        private bool VerificaExistencia(int id)
        {
            return contexto.Clientes.Count(x => x.Id == id) == 1;
        }

        public void Dispose()
        {
            ((IDisposable)contexto).Dispose();
        }
    }
}
